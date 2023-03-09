SUMMARY = "The functions of bflb_mcu_tool is the same as DevCube(MCU View) which is a GUI tool for image programing. "
HOMEPAGE = "https://pypi.org/project/bflb-mcu-tool/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=394cf4b6d65d097bf01176a09f4dd8d0"

SRC_URI[sha256sum] = "ecc71a8ff503838e4920ecc6de2b9fff8266f0ec69d67f887bb4087f69834bf2"
#SRC_URI += "file://add_missing_CHANGES_md.patch"

PYPI_PACKAGE = "bflb-mcu-tool"

PYPI_PACKAGE_EXT = "tar.gz"

inherit pypi setuptools3

BBCLASSEXTEND = "native"

#cklink libraries are x86_64 only
do_install:append() {
    rm -r ${D}${libdir}/python*/site-packages/bflb_mcu_tool/utils/cklink/
}

#don't check arch of things like elf_loader etc which are ISP binaries
INSANE_SKIP:${PN} = "arch"