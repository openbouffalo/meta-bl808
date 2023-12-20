DESCRIPTION = "Bouffalo Labs MCU SDK"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b8b0af6c95458efe12d9bd2c5aa52e9"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI = "git://github.com/bouffalolab/bl_mcu_sdk.git;protocol=https;nobranch=1 \
           file://bflb_fw_post_proc.py \
          "
SRCREV = "ab70ccc953269bb4a35279000beea9013da5ac1c"

PV = "2.0-rc1"
S = "${WORKDIR}/git"

inherit python3native

DEPENDS += " cmake-native python3-native make-native bflb-mcu-tool"
RDEPENDS:${PN} += " cmake make bflb-mcu-tool"

#COMPATIBLE_HOST = "(riscv64|riscv32).*"

BBCLASSEXTEND = "native"

do_install() {
    install -d ${D}/opt/bl_mcu_sdk
    cp -R --no-dereference --preserve=mode,links ${S}/* ${D}/opt/bl_mcu_sdk/
    rm -r ${D}/opt/bl_mcu_sdk/components/crypto/mbedtls/mbedtls/tests
    rm -r ${D}/opt/bl_mcu_sdk/components/crypto/mbedtls/mbedtls/scripts
    rm -r ${D}/opt/bl_mcu_sdk/tools/bflb_tools
    rm -r ${D}/opt/bl_mcu_sdk/tools/cklink_firmware
    rm -r ${D}/opt/bl_mcu_sdk/tools/cmake
    rm -r ${D}/opt/bl_mcu_sdk/tools/dualuart_firmware
    rm -r ${D}/opt/bl_mcu_sdk/tools/kconfig
    rm -r ${D}/opt/bl_mcu_sdk/tools/make
    rm -r ${D}/opt/bl_mcu_sdk/tools/ninja
    rm -r ${D}/opt/bl_mcu_sdk/tools/openocd
    install -d ${D}/opt/bl_mcu_sdk/tools/bflb_tools/bflb_fw_post_proc/
    install -m 755 ${WORKDIR}/bflb_fw_post_proc.py ${D}/opt/bl_mcu_sdk/tools/bflb_tools/bflb_fw_post_proc/bflb_fw_post_proc
}

FILES:${PN} = "/opt/bl_mcu_sdk"
INSANE_SKIP:${PN} = "already-stripped staticdev "
