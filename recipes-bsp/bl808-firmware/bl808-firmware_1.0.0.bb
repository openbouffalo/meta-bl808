DESCRIPTION = "BL808 Firmware Image Creation Tool"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI = " \
	file://mergebin.py \
"

PV = "1.0"

inherit deploy python3native

DEPENDS += " u-boot opensbi lz4-native python3-native"
RDEPENDS:${PN} += " python3-core lz4 u-boot opensbi"


do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/mergebin.py ${D}${bindir}/mergebin.py

}

do_deploy() {
    if [ -f ${DEPLOY_DIR_IMAGE}/u-boot.bin.lz4 ]; then
        rm ${DEPLOY_DIR_IMAGE}/u-boot.bin.lz4
    fi
    lz4 -9 ${DEPLOY_DIR_IMAGE}/u-boot.bin ${DEPLOY_DIR_IMAGE}/u-boot.bin.lz4
    ${WORKDIR}/mergebin.py -o ${DEPLOY_DIR_IMAGE}/bl808-firmware.bin -k ${DEPLOY_DIR_IMAGE}/u-boot.bin.lz4 -d ${DEPLOY_DIR_IMAGE}/u-boot.dtb -s ${DEPLOY_DIR_IMAGE}/fw_jump.bin 
    #rm -r ${DEPLOY_DIR_IMAGE}/u-boot* ${DEPLOY_DIR_IMAGE}/fw_jump.bin ${DEPLOY_DIR_IMAGE}/u-boot.dtb
}

addtask deploy after do_install

COMPATIBLE_HOST = "(riscv64|riscv32).*"
