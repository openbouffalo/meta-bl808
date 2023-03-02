
GLIBC_GIT_URI = "git://github.com/T-head-Semi/glibc.git;protocol=https"
SRCBRANCH = "riscv-glibc-2.33-thead"
PV = "2.33"
SRCREV_glibc = "6aea17dafd37cf8b81c9cca05a8928ebc54f7efb"
SRCREV_localedef = "bd644c9e6f3e20c5504da1488448173c69c56c28"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI += " "


UPSTREAM_CHECK_GITTAGREGEX = "(?P<pver>\d+\.\d+(\.(?!90)\d+)*)"
