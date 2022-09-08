package lib.kalu.ffmpegcmd.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Packages {

    private static final List<String> supportedExternalLibraries;

    static {
        supportedExternalLibraries = new ArrayList<>();
        supportedExternalLibraries.add("fontconfig");
        supportedExternalLibraries.add("freetype");
        supportedExternalLibraries.add("fribidi");
        supportedExternalLibraries.add("gmp");
        supportedExternalLibraries.add("gnutls");
        supportedExternalLibraries.add("kvazaar");
        supportedExternalLibraries.add("mp3lame");
        supportedExternalLibraries.add("libaom");
        supportedExternalLibraries.add("libass");
        supportedExternalLibraries.add("iconv");
        supportedExternalLibraries.add("libilbc");
        supportedExternalLibraries.add("libtheora");
        supportedExternalLibraries.add("libvidstab");
        supportedExternalLibraries.add("libvorbis");
        supportedExternalLibraries.add("libvpx");
        supportedExternalLibraries.add("libwebp");
        supportedExternalLibraries.add("libxml2");
        supportedExternalLibraries.add("opencore-amr");
        supportedExternalLibraries.add("openh264");
        supportedExternalLibraries.add("opus");
        supportedExternalLibraries.add("rubberband");
        supportedExternalLibraries.add("sdl2");
        supportedExternalLibraries.add("shine");
        supportedExternalLibraries.add("snappy");
        supportedExternalLibraries.add("soxr");
        supportedExternalLibraries.add("speex");
        supportedExternalLibraries.add("tesseract");
        supportedExternalLibraries.add("twolame");
        supportedExternalLibraries.add("wavpack");
        supportedExternalLibraries.add("x264");
        supportedExternalLibraries.add("x265");
        supportedExternalLibraries.add("xvid");
    }

    /**
     * Returns enabled external libraries by FFmpeg.
     *
     * @return enabled external libraries
     */
    public static List<String> getExternalLibraries() {

        final List<String> enabledLibraryList = new ArrayList<>();
        for (String supportedExternalLibrary : supportedExternalLibraries) {
            enabledLibraryList.add(supportedExternalLibrary);
        }

        Collections.sort(enabledLibraryList);
        return enabledLibraryList;
    }

    /**
     * Returns MobileFFmpeg binary package name.
     *
     * @return guessed MobileFFmpeg binary package name
     */
    public static String getPackageName() {
        final List<String> externalLibraryList = getExternalLibraries();
        final boolean speex = externalLibraryList.contains("speex");
        final boolean fribidi = externalLibraryList.contains("fribidi");
        final boolean gnutls = externalLibraryList.contains("gnutls");
        final boolean xvid = externalLibraryList.contains("xvid");

        boolean minGpl = false;
        boolean https = false;
        boolean httpsGpl = false;
        boolean audio = false;
        boolean video = false;
        boolean full = false;
        boolean fullGpl = false;

        if (speex && fribidi) {
            if (xvid) {
                fullGpl = true;
            } else {
                full = true;
            }
        } else if (speex) {
            audio = true;
        } else if (fribidi) {
            video = true;
        } else if (xvid) {
            if (gnutls) {
                httpsGpl = true;
            } else {
                minGpl = true;
            }
        } else {
            if (gnutls) {
                https = true;
            }
        }

        if (fullGpl) {
            if (externalLibraryList.contains("fontconfig") &&
                    externalLibraryList.contains("freetype") &&
                    externalLibraryList.contains("fribidi") &&
                    externalLibraryList.contains("gmp") &&
                    externalLibraryList.contains("gnutls") &&
                    externalLibraryList.contains("kvazaar") &&
                    externalLibraryList.contains("mp3lame") &&
                    externalLibraryList.contains("libaom") &&
                    externalLibraryList.contains("libass") &&
                    externalLibraryList.contains("iconv") &&
                    externalLibraryList.contains("libilbc") &&
                    externalLibraryList.contains("libtheora") &&
                    externalLibraryList.contains("libvidstab") &&
                    externalLibraryList.contains("libvorbis") &&
                    externalLibraryList.contains("libvpx") &&
                    externalLibraryList.contains("libwebp") &&
                    externalLibraryList.contains("libxml2") &&
                    externalLibraryList.contains("opencore-amr") &&
                    externalLibraryList.contains("opus") &&
                    externalLibraryList.contains("shine") &&
                    externalLibraryList.contains("snappy") &&
                    externalLibraryList.contains("soxr") &&
                    externalLibraryList.contains("speex") &&
                    externalLibraryList.contains("twolame") &&
                    externalLibraryList.contains("wavpack") &&
                    externalLibraryList.contains("x264") &&
                    externalLibraryList.contains("x265") &&
                    externalLibraryList.contains("xvid")) {
                return "full-gpl";
            } else {
                return "custom";
            }
        }

        if (full) {
            if (externalLibraryList.contains("fontconfig") &&
                    externalLibraryList.contains("freetype") &&
                    externalLibraryList.contains("fribidi") &&
                    externalLibraryList.contains("gmp") &&
                    externalLibraryList.contains("gnutls") &&
                    externalLibraryList.contains("kvazaar") &&
                    externalLibraryList.contains("mp3lame") &&
                    externalLibraryList.contains("libaom") &&
                    externalLibraryList.contains("libass") &&
                    externalLibraryList.contains("iconv") &&
                    externalLibraryList.contains("libilbc") &&
                    externalLibraryList.contains("libtheora") &&
                    externalLibraryList.contains("libvorbis") &&
                    externalLibraryList.contains("libvpx") &&
                    externalLibraryList.contains("libwebp") &&
                    externalLibraryList.contains("libxml2") &&
                    externalLibraryList.contains("opencore-amr") &&
                    externalLibraryList.contains("opus") &&
                    externalLibraryList.contains("shine") &&
                    externalLibraryList.contains("snappy") &&
                    externalLibraryList.contains("soxr") &&
                    externalLibraryList.contains("speex") &&
                    externalLibraryList.contains("twolame") &&
                    externalLibraryList.contains("wavpack")) {
                return "full";
            } else {
                return "custom";
            }
        }

        if (video) {
            if (externalLibraryList.contains("fontconfig") &&
                    externalLibraryList.contains("freetype") &&
                    externalLibraryList.contains("fribidi") &&
                    externalLibraryList.contains("kvazaar") &&
                    externalLibraryList.contains("libaom") &&
                    externalLibraryList.contains("libass") &&
                    externalLibraryList.contains("iconv") &&
                    externalLibraryList.contains("libtheora") &&
                    externalLibraryList.contains("libvpx") &&
                    externalLibraryList.contains("libwebp") &&
                    externalLibraryList.contains("snappy")) {
                return "video";
            } else {
                return "custom";
            }
        }

        if (audio) {
            if (externalLibraryList.contains("mp3lame") &&
                    externalLibraryList.contains("libilbc") &&
                    externalLibraryList.contains("libvorbis") &&
                    externalLibraryList.contains("opencore-amr") &&
                    externalLibraryList.contains("opus") &&
                    externalLibraryList.contains("shine") &&
                    externalLibraryList.contains("soxr") &&
                    externalLibraryList.contains("speex") &&
                    externalLibraryList.contains("twolame") &&
                    externalLibraryList.contains("wavpack")) {
                return "audio";
            } else {
                return "custom";
            }
        }

        if (httpsGpl) {
            if (externalLibraryList.contains("gmp") &&
                    externalLibraryList.contains("gnutls") &&
                    externalLibraryList.contains("libvidstab") &&
                    externalLibraryList.contains("x264") &&
                    externalLibraryList.contains("x265") &&
                    externalLibraryList.contains("xvid")) {
                return "https-gpl";
            } else {
                return "custom";
            }
        }

        if (https) {
            if (externalLibraryList.contains("gmp") &&
                    externalLibraryList.contains("gnutls")) {
                return "https";
            } else {
                return "custom";
            }
        }

        if (minGpl) {
            if (externalLibraryList.contains("libvidstab") &&
                    externalLibraryList.contains("x264") &&
                    externalLibraryList.contains("x265") &&
                    externalLibraryList.contains("xvid")) {
                return "min-gpl";
            } else {
                return "custom";
            }
        }

        if (externalLibraryList.size() == 0) {
            return "min";
        } else {
            return "custom";
        }
    }

}
