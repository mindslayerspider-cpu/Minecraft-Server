# Redmi Note 8 gaming product additions
# Keep this file build-safe: avoid non-existent package names and device-specific
# daemon hooks unless your base ROM/device tree actually provides them.

# Rendering/UI defaults that are generally safe in AOSP-derived trees.
# (Keep values conservative to avoid compatibility regressions.)
PRODUCT_SYSTEM_DEFAULT_PROPERTIES += \
    ro.surface_flinger.use_content_detection_for_refresh_rate=false

# Optional vendor-facing hint for downstream power HAL integrations.
# This key is custom and harmless if unused by your ROM/device services.
PRODUCT_VENDOR_PROPERTIES += \
    ro.vendor.gaming_mode=1

# Copy runtime tuning props.
PRODUCT_COPY_FILES += \
    device/xiaomi/ginkgo-gaming/vendor.prop:$(TARGET_COPY_OUT_VENDOR)/vendor.prop
