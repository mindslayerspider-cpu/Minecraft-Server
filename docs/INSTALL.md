# DexOS 11 (ginkgo) Installation Guide for Users

This guide explains how users can install a **DexOS 11 build** on Redmi Note 8 (`ginkgo`).

## Important

- Unlocking bootloader and flashing custom ROMs can void warranty and erase data.
- Backup all important data before starting.
- This repository provides a **starter/device product layer**. Users ultimately flash a built ROM zip generated from a full Android source tree.

## What users should download

From your release page, users should download:

1. `DexOS-11-ginkgo-<date>.zip` (ROM package)
2. (Optional) `NikGapps`/other Android 11 GApps package (if ROM is vanilla)
3. (Optional) Magisk zip/app for root

## Recovery install steps (TWRP/OrangeFox)

1. Reboot to custom recovery.
2. Backup current ROM (Nandroid backup).
3. Wipe: `Dalvik/ART`, `Cache`, `System`, `Data`.
4. Flash `DexOS-11-ginkgo-<date>.zip`.
5. Flash Android 11 GApps (optional).
6. Format Data if encrypted issues occur.
7. Reboot system.

## First boot checks

- Device boots past setup wizard.
- Wi-Fi/mobile data, camera, audio, and sensors work.
- 20+ minute gaming session has no thermal crash/reboot.

