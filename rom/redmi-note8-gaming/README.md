# Redmi Note 8 (ginkgo) Simple Gaming ROM Starter

This is a **build-safe starter layer** you can drop into an AOSP/LineageOS tree for Redmi Note 8 (`ginkgo`) and then tune for gaming.

> It is not a full ROM by itself. You still need a working device tree, vendor blobs, and kernel source for ginkgo.

## What is included

- `device.mk`: conservative product-level gaming defaults.
- `vendor.prop`: minimal runtime tuning properties.

## Why this version is safer

The previous draft used package names and several properties that may not exist in many ROM trees, which can cause build failures or ineffective tuning. This version keeps only conservative settings and clear extension points.

## How to integrate

1. Copy this folder into your ROM tree, for example:
   - `device/xiaomi/ginkgo-gaming/`
2. In your product makefile, include:

```make
$(call inherit-product, device/xiaomi/ginkgo-gaming/device.mk)
```

3. Build normally:

```bash
source build/envsetup.sh
lunch lineage_ginkgo-userdebug
m bacon -j$(nproc)
```

## Extending for stronger gaming performance

Add device-specific tuning only after validation:

- Kernel CPU scheduler/governor tuning for `sdm665`.
- GPU governor/frequency tables in kernel/vendor stack.
- Power HAL profiles tied to foreground game UID.
- Thermal policy tuning that avoids sustained throttling.

## Validation checklist

- Build passes with no missing package errors.
- No bootloops after flashing.
- Stable thermals during 20–30 minute gaming sessions.
- No modem/Wi-Fi regressions.
