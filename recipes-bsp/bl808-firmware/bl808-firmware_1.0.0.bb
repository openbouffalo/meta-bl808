DESCRIPTION = "BL808 Firmware Image Creation Tool"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI = " \
	file://mergebin.py \
"

PV = "1.0"
PR = "r1"

inherit deploy python3native

DEPENDS = "u-boot opensbi lz4-native"

do_install() {
    install -d ${D}${bindir}
    install -d ${D}/lib/firmware/bl808/
    install -m 0755 ${WORKDIR}/mergebin.py ${D}${bindir}/mergebin.py
    lz4 -f9 ${DEPLOY_DIR_IMAGE}/u-boot.bin ${D}/lib/firmware/bl808/u-boot.bin.lz4
    chown root.root ${D}/lib/firmware/bl808/u-boot.bin.lz4
    install -m 0755 ${DEPLOY_DIR_IMAGE}/u-boot.dtb ${D}/lib/firmware/bl808/u-boot.dtb
    install -m 0755 ${DEPLOY_DIR_IMAGE}/fw_jump.bin ${D}/lib/firmware/bl808/fw_jump.bin
    ${WORKDIR}/mergebin.py -o ${D}/lib/firmware/bl808/bl808-firmware.bin -k ${D}/lib/firmware/bl808/u-boot.bin.lz4 -d ${D}/lib/firmware/bl808/u-boot.dtb -s ${D}/lib/firmware/bl808/fw_jump.bin 
}

do_deploy() {
    install ${D}/lib/firmware/bl808/bl808-firmware.bin ${DEPLOYDIR}/bl808-firmware.bin
}

FILES:${PN} += "${bindir}/mergebin.py \
        /lib/firmware/bl808/ \
        "

addtask deploy after do_install

COMPATIBLE_HOST = "(riscv64|riscv32).*"
