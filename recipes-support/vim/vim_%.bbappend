#we need to link with librt for this to work correctly
EXTRA_OECONF:remove:class-target = " vim_cv_timer_create=yes"
EXTRA_OECONF:append:class-target = " vim_cv_timer_create_with_lrt=yes"

