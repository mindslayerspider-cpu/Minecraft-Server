import bpy, math
from mathutils import Vector

# Original Minecraft-inspired red scythe cinematic.
# Run in Blender 3.x/4.x. The script builds the scene and animation automatically.

# Clear scene
bpy.ops.object.select_all(action='SELECT')
bpy.ops.object.delete(use_global=False)
for datablocks in (bpy.data.meshes, bpy.data.curves, bpy.data.materials, bpy.data.cameras, bpy.data.lights):
    pass

FPS = 60
END = 600  # 10 seconds
scene = bpy.context.scene
scene.render.engine = 'BLENDER_EEVEE_NEXT'
scene.render.resolution_x = 1920
scene.render.resolution_y = 1080
scene.render.resolution_percentage = 100
scene.render.fps = FPS
scene.frame_start = 1
scene.frame_end = END
scene.render.image_settings.file_format = 'FFMPEG'
scene.render.ffmpeg.format = 'MPEG4'
scene.render.ffmpeg.codec = 'H264'
scene.render.filepath = '//red_scythe_intro.mp4'
scene.world.color = (0.003, 0.003, 0.006)

# Materials
def mat(name, color, metallic=0.0, rough=0.4, emission=None, strength=0):
    m=bpy.data.materials.new(name); m.diffuse_color=(*color,1)
    m.use_nodes=True
    bs=m.node_tree.nodes.get('Principled BSDF')
    bs.inputs['Base Color'].default_value=(*color,1)
    bs.inputs['Metallic'].default_value=metallic
    bs.inputs['Roughness'].default_value=rough
    if emission:
        bs.inputs['Emission Color'].default_value=(*emission,1)
        bs.inputs['Emission Strength'].default_value=strength
    return m
metal=mat('Scythe Dark Metal',(0.025,0.018,0.02),0.85,0.22)
red=mat('Blade Crimson',(0.18,0.005,0.008),0.7,0.18)
glow=mat('Red Energy',(0.8,0.005,0.01),0.1,0.15,(1,0.005,0.01),12)
ground=mat('Obsidian Ground',(0.012,0.012,0.016),0.2,0.65)

# Helpers
def cube(name, loc, scale, material, bevel=0.04):
    bpy.ops.mesh.primitive_cube_add(location=loc); o=bpy.context.object; o.name=name; o.scale=scale; bpy.ops.object.transform_apply(location=False,rotation=False,scale=True)
    if bevel:
        mod=o.modifiers.new('Small block bevel','BEVEL'); mod.width=bevel; mod.segments=2
    o.data.materials.append(material); return o

def cylinder(name, loc, radius, depth, material, vertices=12):
    bpy.ops.mesh.primitive_cylinder_add(vertices=vertices, radius=radius, depth=depth, location=loc); o=bpy.context.object; o.name=name; o.data.materials.append(material); return o

# Scythe root
root=bpy.data.objects.new('SCYTHE_ROOT',None); bpy.context.collection.objects.link(root)
root.location=(0,0,0)

# Blocky handle
handle=cube('Blocky Handle',(0,0,0.15),(0.12,0.12,2.7),metal,0.035); handle.parent=root
# grip segments
for z in [-1.7,-1.25,-0.8,-0.35,0.1,0.55,1.0,1.45,1.9,2.35]:
    g=cube('GripBlock',(0,-0.01,z),(0.16,0.16,0.10),red,0.02); g.parent=root

# Blade made from stepped cubes for a Minecraft-compatible silhouette
blade_parts=[(-0.15,2.95,0.38,0.16),(-0.45,3.20,0.42,0.16),(-0.78,3.43,0.42,0.16),(-1.10,3.55,0.34,0.16),(-1.40,3.50,0.28,0.16),(-1.62,3.28,0.22,0.16),(-1.72,2.98,0.18,0.16)]
for i,(x,z,sx,sy) in enumerate(blade_parts):
    b=cube(f'BladeBlock_{i}',(x,0,z),(sx,sy,0.14),red,0.025); b.parent=root
    e=cube(f'GlowEdge_{i}',(x,-0.17,z),(sx*0.88,0.035,0.045),glow,0.01); e.parent=root
# blade tip
for x,z in [(-1.84,2.68),(-1.92,2.38)]:
    b=cube('BladeTip',(x,0,z),(0.12,0.16,0.22),red,0.02); b.rotation_euler[1]=math.radians(-18); b.parent=root

# Energy curve following blade
curve=bpy.data.curves.new('EnergyTrail','CURVE'); curve.dimensions='3D'; curve.bevel_depth=0.045; curve.bevel_resolution=3
s=curve.splines.new('BEZIER'); s.bezier_points.add(7)
pts=[(-0.1,-0.2,3.0),(-0.5,-0.22,3.25),(-0.9,-0.22,3.48),(-1.3,-0.22,3.55),(-1.6,-0.22,3.25),(-1.75,-0.22,2.85),(-1.85,-0.22,2.45),(-1.9,-0.22,2.15)]
for p,co in zip(s.bezier_points,pts): p.co=co; p.handle_left_type=p.handle_right_type='AUTO'
trail=bpy.data.objects.new('Red Energy Trail',curve); bpy.context.collection.objects.link(trail); curve.materials.append(glow); trail.parent=root

# Ground blocks
for x in range(-6,7):
  for y in range(-4,5):
    c=cube('GroundBlock',(x*1.4,y*1.4,-3.0),(0.68,0.68,0.18),ground,0.02)

# Particles / embers using instanced emissive cubes
for i in range(45):
    a=i*2.399; r=2.0+(i%7)*0.42
    x=math.cos(a)*r; y=math.sin(a)*r; z=-2.2+(i%13)*0.43
    p=cube('Red Ember',(x,y,z),(0.025,0.025,0.025),glow,0.005)
    p.keyframe_insert('location',frame=1)
    p.location.z += 0.7+(i%5)*0.15; p.keyframe_insert('location',frame=END)

# Main light and red rim
bpy.ops.object.light_add(type='AREA', location=(3,-4,4)); key=bpy.context.object; key.name='Key Light'; key.data.energy=850; key.data.shape='DISK'; key.data.size=5
key.rotation_euler=(math.radians(35),0,math.radians(35))
bpy.ops.object.light_add(type='AREA', location=(-4,1,2)); rim=bpy.context.object; rim.name='Red Rim'; rim.data.energy=1200; rim.data.color=(1,0.01,0.01); rim.data.size=4; rim.rotation_euler=(math.radians(70),0,math.radians(-65))

# Camera
bpy.ops.object.camera_add(location=(0,-9,2.0)); cam=bpy.context.object; cam.name='Cinematic Camera'; scene.camera=cam; cam.data.lens=48

def look_at(obj, target): obj.rotation_euler=(Vector(target)-obj.location).to_track_quat('-Z','Y').to_euler()
look_at(cam,(0,0,1))
# Close blade start -> pullback -> final hero
cam.location=(-1.7,-4.2,3.0); look_at(cam,(-0.7,0,3.0)); cam.keyframe_insert('location',frame=1); cam.keyframe_insert('rotation_euler',frame=1)
cam.location=(0,-7.2,1.3); look_at(cam,(-0.3,0,1.0)); cam.keyframe_insert('location',frame=210); cam.keyframe_insert('rotation_euler',frame=210)
cam.location=(0.5,-10.5,1.1); look_at(cam,(-0.3,0,0.8)); cam.keyframe_insert('location',frame=360); cam.keyframe_insert('rotation_euler',frame=360)
cam.location=(0.4,-9.0,1.5); look_at(cam,(-0.4,0,1.0)); cam.keyframe_insert('location',frame=450); cam.keyframe_insert('rotation_euler',frame=450)
cam.location=(0.4,-9.0,1.5); cam.keyframe_insert('location',frame=END); cam.keyframe_insert('rotation_euler',frame=END)

# Scythe swing/rotation
root.rotation_euler=(0,0,math.radians(-35)); root.keyframe_insert('rotation_euler',frame=1)
root.rotation_euler=(0,0,math.radians(35)); root.keyframe_insert('rotation_euler',frame=130)
root.rotation_euler=(0,0,math.radians(-8)); root.keyframe_insert('rotation_euler',frame=220)
root.rotation_euler=(0,0,math.radians(4)); root.keyframe_insert('rotation_euler',frame=300)
root.rotation_euler=(0,0,0); root.keyframe_insert('rotation_euler',frame=390)
root.rotation_euler=(0,0,math.radians(3)); root.keyframe_insert('rotation_euler',frame=END)

# Impact shake around final position
for f,dx,dz in [(385,0.00,0.00),(392,0.07,-0.04),(398,-0.06,0.03),(404,0.03,-0.015),(412,0,0)]:
    cam.location.x=0.4+dx; cam.location.z=1.5+dz; cam.keyframe_insert('location',frame=f)

# Smooth interpolation
for obj in [cam,root]:
    if obj.animation_data and obj.animation_data.action:
        for fc in obj.animation_data.action.fcurves:
            for kp in fc.keyframe_points: kp.interpolation='BEZIER'

# Compositor glow
scene.use_nodes=True
nt=scene.node_tree; nt.nodes.clear()
rl=nt.nodes.new('CompositorNodeRLayers'); glare=nt.nodes.new('CompositorNodeGlare'); glare.glare_type='FOG_GLOW'; glare.quality='HIGH'; glare.threshold=0.6; glare.size=7
comp=nt.nodes.new('CompositorNodeComposite'); nt.links.new(rl.outputs['Image'],glare.inputs['Image']); nt.links.new(glare.outputs['Image'],comp.inputs['Image'])

scene.frame_set(1)
print('Red scythe cinematic created. Render: red_scythe_intro.mp4')
