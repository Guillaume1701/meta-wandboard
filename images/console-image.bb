SUMMARY = "A console development image with some C/C++ dev tools"
HOMEPAGE = "http://www.jumpnowtek.com"
LICENSE = "MIT"

IMAGE_FEATURES += "package-management"
IMAGE_LINGUAS = "en-us"

inherit core-image

CORE_OS = " \
    openssh openssh-keygen openssh-sftp-server \
    term-prompt \
    tzdata \
 "

KERNEL_EXTRA_INSTALL = " \
    kernel-modules \
 "

WIFI_SUPPORT = " \
    bcm4329-nvram-config \
    bcm4330-nvram-config \
    crda \
    iw \
    wireless-tools \
    wpa-supplicant \
 "

DEV_SDK_INSTALL = " \
    binutils \
    binutils-symlinks \
    coreutils \
    cpp \
    cpp-symlinks \
    diffutils \
    file \
    gcc \
    gcc-symlinks \
    g++ \
    g++-symlinks \
    gettext \
    git \
    ldd \
    libstdc++ \
    libstdc++-dev \
    libtool \
    make \
    perl-modules \
    pkgconfig \
 "

DEV_EXTRAS = " \
    ntp \
    ntp-tickadj \
    serialecho \
    spiloop \
 "

PYTHON_SUPPORT = " \
    python-core \
    python-fcntl \
    python-subprocess \
 "

EXTRA_TOOLS_INSTALL = " \
    bc \
    bzip2 \
    ethtool \
    findutils \
    i2c-tools \
    iftop \
    iperf \
    htop \
    less \
    nano \
    procps \
    rsync \
    sysfsutils \
    tcpdump \
    util-linux \
    unzip \
    wget \
    zip \
 "

IMAGE_INSTALL += " \
    ${CORE_OS} \
    ${DEV_SDK_INSTALL} \
    ${DEV_EXTRAS} \
    ${EXTRA_TOOLS_INSTALL} \
    ${KERNEL_EXTRA_INSTALL} \
    ${WIFI_SUPPORT} \
 "

IMAGE_FILE_BLACKLIST += " \
    /etc/init.d/hwclock.sh \
 "

remove_blacklist_files() {
    for i in ${IMAGE_FILE_BLACKLIST}; do
        rm -rf ${IMAGE_ROOTFS}$i
    done
}

set_local_timezone() {
    ln -sf /usr/share/zoneinfo/EST5EDT ${IMAGE_ROOTFS}/etc/localtime
}

disable_bootlogd() {
    echo BOOTLOGD_ENABLE=no > ${IMAGE_ROOTFS}/etc/default/bootlogd
}

ROOTFS_POSTPROCESS_COMMAND += " \
    remove_blacklist_files ; \
    set_local_timezone ; \
    disable_bootlogd ; \
 "

export IMAGE_BASENAME = "console-image"

