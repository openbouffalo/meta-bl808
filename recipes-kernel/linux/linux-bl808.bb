SECTION = "kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc


LINUX_VERSION ?= "v6.2"
KERNEL_VERSION_SANITY_SKIP="1"
SRCPV = "${@bb.fetch2.get_srcrev(d)}"
PR = "r1"
PV = "${LINUX_VERSION}"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

BRANCH = "master"
SRCREV = "c9c3395d5e3dcc6daee66c6908354d47bf98cb0c"

SRC_URI =   "git://github.com/torvalds/linux.git;branch=${BRANCH};protocol=https \
            file://0002-serial-bflb_uart-add-Bouffalolab-UART-Driver.patch \
            file://0003-MAINTAINERS-add-myself-as-a-reviewer-for-Bouffalolab.patch \
            file://0004-riscv-add-the-Bouffalolab-SoC-family-Kconfig-option.patch \
            file://0005-riscv-dts-bouffalolab-add-the-bl808-SoC-base-device-.patch \
            file://0006-riscv-dts-bouffalolab-add-Sipeed-M1S-dock-devicetree.patch \
            file://0007-MAINTAINERS-add-myself-as-Bouffalolab-SoC-entry-main.patch \
            file://0009-riscv-dts-bouffalolab-add-bootargs-initrd.patch \
            file://0010-riscv-dts-bouffalolab-add-xip_flash.patch \
            file://0011-WIP-add-BFLB-MBOX-interrupt-controller-driver.patch \
            file://0012-WIP-sdhci-add-BFLB-sdhci-driver.patch \
            file://0013-disable-card-detection-dma-cap-clock-quirks.patch \
            file://0014-UART2-working-under-Linux.patch \
            file://0015-dts-bl808-add-fake-sdh-clock-at-96MHz.patch \
            file://0016-sdhci-bflb-enable-additional-quirks.patch \
            file://0017-Disable-flash-rootfs-for-now-edit-bootargs-to-use-SD.patch \
            file://0018-rename-dts-files.patch \
            file://0020-tty-serial-bflb_uart-fix-leaked-ISR-registration.patch \
            file://0021-mmc-sdhci-bflb-remove-unnecessary-quirks.patch \
            file://0024-riscv-dts-bflb-m1s-Fix-address-size-cells.patch \
            file://0025-riscv-dts-bflb-ox64-Fix-address-size-cells.patch \
            file://0026-Add-timer-node-for-OpenSBI-1.2-compatibility.patch \
            file://0027-Tabstops-are-8-chars-not-4.patch \
            file://0028-Add-GPIO-PINCTRL-and-HWRNG-Crypto-drivers.patch \
            file://0029-Update-device-trees-for-new-GPIO-and-HWRNG-drivers.patch \
            file://0031-Bring-M1s-device-tree-up-to-date-with-Ox64-s-changes.patch \
            file://defconfig"

KERNEL_DEVICETREE = "bouffalolab/bl808-sipeed-m1s.dtb bouffalolab/bl808-pine64-ox64.dtb"

COMPATIBLE_MACHINE = "(bl808|pine64-ox64|sipeed-m1s)"
COMPATIBLE_HOST = "(riscv64-poky-linux|riscv64-poky-elf|riscv64-bflb-linux)"

KCONFIG_MODE = "--alldefconfig"