require recipes-bsp/u-boot/u-boot-common.inc
require recipes-bsp/u-boot/u-boot.inc

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = " git://github.com/openbouffalo/u-boot.git;protocol=https;branch=bl808/all \
            file://0001-add-default-script-address.patch \
            file://boot-m1s.cmd \
            file://boot-pine64.cmd \
            file://defconfig \
           "
SRCREV = "b96565ca8ebfc9d38f3c09d6c16ed4cf1f0c806e"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"

DEPENDS:append = " u-boot-tools-native"



do_configure:prepend() {
    cp ${WORKDIR}/defconfig ${S}/configs/ox64_d0_defconfig
    mkimage -O linux -T script -C none -n "U-Boot M1S boot script" \
        -d ${WORKDIR}/boot-m1s.cmd ${WORKDIR}/boot-m1s.scr
    mkimage -O linux -T script -C none -n "U-Boot Pine64 boot script" \
        -d ${WORKDIR}/boot-pine64.cmd ${WORKDIR}/boot-pine64.scr
}

do_deploy:append() {
    if [ -f "${WORKDIR}/boot-m1s.scr" ]; then
        install -d ${DEPLOY_DIR_IMAGE}
        install -m 755 ${WORKDIR}/boot-m1s.scr ${DEPLOY_DIR_IMAGE}
    fi
    if [ -f "${WORKDIR}/boot-pine64.scr" ]; then
        install -d ${DEPLOY_DIR_IMAGE}
        install -m 755 ${WORKDIR}/boot-pine64.scr ${DEPLOY_DIR_IMAGE}
        cp ${WORKDIR}/boot-pine64.scr ${DEPLOY_DIR_IMAGE}/boot.scr
    fi
    install -m 755 ${B}/u-boot.dtb ${DEPLOY_DIR_IMAGE}
}

FILES:${PN}:append = " /boot/boot-m1s.scr /boot/boot-pine64.scr /boot/boot.scr"
