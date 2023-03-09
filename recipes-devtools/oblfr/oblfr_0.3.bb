DESCRIPTION = "OpenBouffalo Firmware"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b8b0af6c95458efe12d9bd2c5aa52e9"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI = "git://github.com/openbouffalo/OBLFR.git;protocol=https;branch=master \
          "
SRCREV = "2abd79917d67cc8213f71ee6da23d0ab83440f07"

PV = "0.3"
S = "${WORKDIR}/git"

DEPENDS += " bl-mcu-sdk bflb-mcu-tool"
RDEPENDS:${PN} += " bl-mcu-sdk bflb-mcu-tool"

#COMPATIBLE_HOST = "(riscv64|riscv32).*"

BBCLASSEXTEND = "native"

do_install() {
    install -d ${D}/opt/oblfr
    cp -R --no-dereference --preserve=mode,links ${S}/* ${D}/opt/oblfr/
}

FILES:${PN} = "/opt/oblfr"
#INSANE_SKIP:${PN} = "already-stripped staticdev "