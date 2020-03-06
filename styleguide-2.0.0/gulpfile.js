"use strict";

const plumber = require("gulp-plumber");
const autoprefixer = require("gulp-autoprefixer");
const cleancss = require("gulp-clean-css");
const uglify = require("gulp-uglify");
const del = require("del");
const rename = require("gulp-rename");
const gulp = require("gulp");
const merge = require("merge-stream");
const sass = require("gulp-sass");
const dartsass = require("dart-sass");

sass.compiler = dartsass;


function modules() {
    var bootstrapjs = gulp.src("./node_modules/bootstrap/dist/js/*.min.js")
        .pipe(gulp.dest("./src/main/resources/static/js"));
    var bootstrapcss = gulp.src("./node_modules/bootstrap/dist/css/bootstrap.min.css")
        .pipe(gulp.dest("./src/main/resources/static/css"));
    var bootstrapcssmapfile = gulp.src("./node_modules/bootstrap/dist/css/bootstrap.min.css.map")
        .pipe(gulp.dest("./src/main/resources/static/css"));
    var iconocss = gulp.src("./node_modules/icono/dist/*.min.css")
        .pipe(gulp.dest("./src/main/resources/static/css"));
    return merge(bootstrapjs, bootstrapcss, bootstrapcssmapfile, iconocss);
}

function compile_sass() {
    return gulp
        .src("./src/main/resources/static/css/**/*.scss")
        .pipe(plumber())
        .pipe(sass({
            outputStyle: "compressed",
            includePaths: "./node_modules",
        }))
        .on("error", sass.logError)
        .pipe(autoprefixer({
            cascade: false
        }))
        .pipe(gulp.dest("./build/css"));
}

function minify_css() {
    return gulp
        .src("./build/css/**/*.css")
        .pipe(cleancss())
        .pipe(rename({
            suffix: ".min"
        }))
        .pipe(gulp.dest("./src/main/resources/static/css"));
}


function js() {
    return gulp
        .src([
            "./src/main/resources/static/js/**/*.js",
            "!./src/main/resources/static/js/*.min.js",
        ])
        .pipe(uglify())
        .pipe(rename({
            suffix: ".min"
        }))
        .pipe(gulp.dest("./src/main/resources/static/js"));
}


const css = gulp.series(compile_sass, minify_css);
const build = gulp.series(modules, gulp.parallel(css, js));

exports.build = build;
exports.default = build;