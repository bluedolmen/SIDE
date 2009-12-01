svgFile="$1"
pngFile="$2"

MAGICK_TMPDIR=/tmp
export MAGICK_TMPDIR

/usr/local/bin/convert "$svgFile" "$pngFile"