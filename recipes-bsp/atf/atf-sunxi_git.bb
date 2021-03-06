DESCRIPTION = "ARM Trusted Firmware Allwinner"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://license.rst;md5=e927e02bca647e14efd87e9e914b2443"
FILESEXTRAPATHS_prepend := "${THISDIR}/files/:"

SRC_URI = "git://github.com/ARM-software/arm-trusted-firmware;branch=master \
           file://0001-allwinner-H5-Implement-power-down-for-Emlid-Neutis-N5.patch \
           "
SRCREV = "7db0c96023281d8a530f5e011a232e5d56557437"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

COMPATIBLE_MACHINE = "(sun50i)"

PLATFORM_sun50i = "sun50i_a64"

LDFLAGS[unexport] = "1"

do_compile() {
    oe_runmake -C ${S} BUILD_BASE=${B} \
      CROSS_COMPILE=${TARGET_PREFIX} \
      PLAT=${PLATFORM} \
      DEBUG=1 \
      bl31
}

do_install() {
    install -D -p -m 0644 ${B}/${PLATFORM}/debug/bl31.bin ${DEPLOY_DIR_IMAGE}/bl31.bin
}

do_install[nostamp] = "1"
