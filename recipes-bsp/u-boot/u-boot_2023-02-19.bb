require recipes-bsp/u-boot/u-boot-common.inc
require recipes-bsp/u-boot/u-boot.inc

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "git://github.com/openbouffalo/u-boot.git;protocol=https;branch=bl808/all \
           file://0001-add-default-script-address.patch \
           file://boot-m1s.cmd \
           file://boot-pine64.cmd \
           file://defconfig \
           file://0002-add-a-few-more-variables-for-extlinux-loading-to-wor.patch \
           "
SRCREV = "b96565ca8ebfc9d38f3c09d6c16ed4cf1f0c806e"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"

DEPENDS:append = " u-boot-tools-native"
UBOOT_EXTLINUX = "1"
UBOOT_EXTLINUX_ROOT = "root=PARTLABEL=rootfs"
UBOOT_EXTLINUX_CONSOLE = "console=ttyS0,2000000 loglevel=8 earlycon=sbi"
UBOOT_EXTLINUX_KERNEL_ARGS = "rootwait rw rootfstype=ext4"
UBOOT_EXTLINUX_INSTALL_DIR ?= "/boot/extlinux"
UBOOT_EXTLINUX_CONF_NAME ?= "extlinux.conf"
UBOOT_EXTLINUX_SYMLINK ?= "${UBOOT_EXTLINUX_CONF_NAME}"
UBOOT_EXTLINUX_KERNEL_IMAGE = "../Image"

UBOOT_EXTLINUX_LABELS = "pine64 m1sdock"
UBOOT_EXTLINUX_DEFAULT_LABEL ??= "Pine64 OX64 Kernel"
UBOOT_EXTLINUX_TIMEOUT ??= "30"


UBOOT_EXTLINUX_MENU_DESCRIPTION:pine64 = "Pine64 0X64 Kernel"
UBOOT_EXTLINUX_FDT:pine64 = "../bl808-pine64-ox64.dtb"

UBOOT_EXTLINUX_MENU_DESCRIPTION:m1sdock = "Sipeed M1SDock Kernel"
UBOOT_EXTLINUX_FDT:m1sdock = "../bl808-sipeed-m1s.dtb"


inherit uboot-extlinux-config

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
    
    if [ "${UBOOT_EXTLINUX}" = "1" ]
    then
        install -m 644 ${UBOOT_EXTLINUX_CONFIG} ${DEPLOYDIR}/${UBOOT_EXTLINUX_SYMLINK}
    fi
}

FILES:${PN}:append = " /boot/boot-m1s.scr /boot/boot-pine64.scr /boot/boot.scr"

