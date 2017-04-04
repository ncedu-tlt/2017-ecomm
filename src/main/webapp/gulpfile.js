var gulp = require('gulp');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var sourcemaps = require('gulp-sourcemaps');
var autoprefixer = require('gulp-autoprefixer');
var gutil = require("gulp-util");
var webpack = require('webpack');

var webpackConfig = require('./webpack.config');

//--------------------------------------------------
//-------------- Vendor Tasks - Start --------------
//--------------------------------------------------

gulp.task('vendor-js', function () {
    var files = [
        './bower_components/jquery/dist/jquery.min.js',
        './bower_components/semantic/dist/semantic.js'
    ];
    return gulp.src(files)
        .pipe(sourcemaps.init())
        .pipe(uglify())
        .pipe(concat('vendor.js'))
        .pipe(sourcemaps.write('./'))
        .pipe(gulp.dest('./dist/vendor'));
});

gulp.task('vendor-themes', function () {
    var files = [
        './bower_components/semantic/dist/themes/default/**/*'
    ];
    return gulp.src(files)
        .pipe(gulp.dest('./dist/vendor/themes/default'));
});

gulp.task('vendor-css', ['vendor-themes'], function () {
    var files = [
        './bower_components/semantic/dist/semantic.css'
    ];
    return gulp.src(files)
        .pipe(sourcemaps.init())
        .pipe(concat('vendor.css'))
        .pipe(sourcemaps.write('./'))
        .pipe(gulp.dest('./dist/vendor'));
});

gulp.task('build-vendor', [
    'vendor-js',
    'vendor-css'
]);

//--------------------------------------------------
//--------------- Vendor Tasks - End ---------------
//--------------------------------------------------



//--------------------------------------------------
//--------------- Shop Tasks - Start ---------------
//--------------------------------------------------

gulp.task('shop-js-framework', function () {
    var files = [
        'src/framework/**/*.js'
    ];
    return gulp.src(files)
        .pipe(sourcemaps.init())
        .pipe(concat('framework.js'))
        .pipe(uglify())
        .pipe(sourcemaps.write('./'))
        .pipe(gulp.dest('./dist/shop/js'));
});

gulp.task('shop-js', function () {
    var files = [
        'src/app/**/*.js'
    ];
    return gulp.src(files)
        .pipe(sourcemaps.init())
        .pipe(concat('app.js'))
        .pipe(uglify())
        .pipe(sourcemaps.write('./'))
        .pipe(gulp.dest('./dist/shop/js'));
});


gulp.task('shop-css', function () {
    var files = [
        'src/app/**/*.css'
    ];
    return gulp.src(files)
        .pipe(sourcemaps.init())
        .pipe(concat('app.css'))
        .pipe(autoprefixer())
        .pipe(sourcemaps.write('./'))
        .pipe(gulp.dest('./dist/shop/css'));
});

gulp.task('build-shop', [
    'shop-js-framework',
    'shop-js',
    'shop-css'
]);

//--------------------------------------------------
//---------------- Shop Tasks - End ----------------
//--------------------------------------------------



//--------------------------------------------------
//----------- Management Tasks - Start ----------
//--------------------------------------------------

gulp.task('build-management', function(callback) {
    webpack(webpackConfig, function(err, stats) {
        if(err) throw new gutil.PluginError("webpack", err);
        gutil.log("[webpack]", stats.toString({
            // output options
        }));
        callback();
    });
});

//--------------------------------------------------
//------------ Management Tasks - End -----------
//--------------------------------------------------

gulp.task('build', [
    'build-vendor',
    'build-shop',
    'build-management'
]);

gulp.task('watch', function () {
    gulp.watch('./src/framework/**/*.js', ['shop-js-framework']);
    gulp.watch('./src/app/**/*.js', ['shop-js']);
    gulp.watch('./src/app/**/*.css', ['shop-css']);
});

gulp.task('default', ['build', 'watch']);
