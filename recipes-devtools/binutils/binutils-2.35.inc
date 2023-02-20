LIC_FILES_CHKSUM="\
    file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552\
    file://COPYING.LIB;md5=9f604d8a4f8e74f4f5140845a21b6674\
    file://COPYING3;md5=d32239bcb673463ab874e80d47fae504\
    file://COPYING3.LIB;md5=6a6a8e020838b23406c81b19c1d46df6\
    file://gas/COPYING;md5=d32239bcb673463ab874e80d47fae504\
    file://include/COPYING;md5=59530bdf33659b29e73d4adb9f9f6552\
    file://include/COPYING3;md5=d32239bcb673463ab874e80d47fae504\
    file://libiberty/COPYING.LIB;md5=a916467b91076e631dd8edb7424769c7\
    file://bfd/COPYING;md5=d32239bcb673463ab874e80d47fae504\
    "

# When upgrading to 2.39, please make sure there is no trailing .0, so
# that upstream version check can work correctly.
PV = "2.35"
CVE_VERSION = "2.35"
SRCBRANCH ?= "xuantie-binutils-gdb-2.35"

#UPSTREAM_CHECK_GITTAGREGEX = "binutils-(?P<pver>\d+_(\d_?)*)"
#git://github.com/T-head-Semi/binutils-gdb.git;branch=xuantie-binutils-gdb-2.35;protocol=https
SRCREV ?= "b34ac3415950d057a58ae55b99ee3829faa7acb7"
BINUTILS_GIT_URI ?= "git://github.com/T-head-Semi/binutils-gdb.git;branch=${SRCBRANCH};protocol=https"
SRC_URI = "${BINUTILS_GIT_URI}"
S  = "${WORKDIR}/git"
