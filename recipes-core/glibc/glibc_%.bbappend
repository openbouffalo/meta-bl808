#GLIBC_GIT_URI = "git://github.com/T-head-Semi/glibc.git"
#SRCBRANCH = "riscv-glibc-2.33-thead"
#PV = "2.33"
#SRCREV_glibc = "6aea17dafd37cf8b81c9cca05a8928ebc54f7efb"
#SRCREV_localedef ?= "794da69788cbf9bf57b59a852f9f11307663fa87"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI += " file://0179-sunrpc-Suppress-GCC-Os-warning-on-user2netname.patch"
