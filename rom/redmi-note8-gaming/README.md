# DexOS 11 for Redmi Note 8 (ginkgo) — Gaming ROM Starter

This is a **build-safe starter layer** for **DexOS (Android 11 / R)** on Redmi Note 8 (`ginkgo`) that you can drop into an AOSP/LineageOS tree and then tune for gaming.

> It is not a full ROM by itself. You still need a working device tree, vendor blobs, and kernel source for ginkgo.

## What is included

- `AndroidProducts.mk`: registers DexOS Android 11 lunch target(s).
- `dexos_ginkgo.mk`: DexOS 11 product definition for ginkgo.
- `device.mk`: conservative product-level gaming defaults.
- `vendor.prop`: minimal runtime tuning properties.

## Android version target

- Base target: **Android 11 (R)**.
- Typical source bases: AOSP 11 or LineageOS 18.1 trees for `ginkgo`.

## Why this version is safer

This starter avoids non-guaranteed package names and aggressive defaults that can cause build failures or unstable runtime behavior across different ROM trees.

## How to integrate

1. Copy this folder into your ROM tree, for example:
   - `device/xiaomi/ginkgo-gaming/`
2. Ensure your build system sees `AndroidProducts.mk` in this folder.
3. Build DexOS 11:

```bash
source build/envsetup.sh
lunch dexos_ginkgo-userdebug
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
