# DexOS product definition for Redmi Note 8 (ginkgo)

$(call inherit-product, device/xiaomi/ginkgo/device.mk)
$(call inherit-product, device/xiaomi/ginkgo-gaming/device.mk)

PRODUCT_DEVICE := ginkgo
PRODUCT_NAME := dexos_ginkgo
PRODUCT_BRAND := Xiaomi
PRODUCT_MODEL := Redmi Note 8
PRODUCT_MANUFACTURER := Xiaomi

# DexOS identity
PRODUCT_SYSTEM_DEFAULT_PROPERTIES += \
    ro.product.system.name=DexOS \
    ro.modversion=DexOS-ginkgo
