DESCRIPTION = "Mainline Linux Kernel"
SECTION = "kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

LINUX_VERSION_EXTENSION = ""

PV = "${LINUX_VERSION}"

KCONFIG_MODE="--alldefconfig"

KBUILD_DEFCONFIG:bl808 = "defconfig"

COMPATIBLE_MACHINE = "bl808"

KERNEL_DEVICETREE:bl808 = "bouffalolab/bl808-sipeed-m1s bouffalolab/bl808-pine64-ox64"

