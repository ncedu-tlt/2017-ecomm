var gulp = require('gulp');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var sourcemaps = require('gulp-sourcemaps');
var autoprefixer = require('gulp-autoprefixer');
var ngAnnotate = require('gulp-ng-annotate');
var embedTemplates = require('gulp-angular-embed-templates');
var gutil = require("gulp-util");
var webpack = require('webpack');

var webpackConfig = require('./webpack.config');

//--------------------------------------------------
//--------------- Shop Tasks - Start ---------------
//--------------------------------------------------

gulp.task('shop-js-libs', function () {
    var files = [
        './bower_components/jquery/dist/jquery.min.js',
        './bower_components/semantic/dist/semantic.js'
    ];
    return gulp.src(files)
        .pipe(sourcemaps.init())
        .pipe(uglify())
        .pipe(concat('vendor.js'))
        .pipe(sourcemaps.write('./'))
        .pipe(gulp.dest('./dist/shop/js'));
});

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

gulp.task('shop-copy-themes', function () {
    var files = [
        './bower_components/semantic/dist/themes/default/**/*'
    ];
    return gulp.src(files)
        .pipe(gulp.dest('./dist/shop/css/themes/default'));
});

gulp.task('shop-css-libs', ['shop-copy-themes'], function () {
    var files = [
        './bower_components/semantic/dist/semantic.css'
    ];
    return gulp.src(files)
        .pipe(sourcemaps.init())
        .pipe(concat('vendor.css'))
        .pipe(sourcemaps.write('./'))
        .pipe(gulp.dest('./dist/shop/css'));
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
    'shop-js-libs',
    'shop-js-framework',
    'shop-js',
    'shop-css-libs',
    'shop-css'
]);

//--------------------------------------------------
//---------------- Shop Tasks - End ----------------
//--------------------------------------------------


//--------------------------------------------------
//------------ Management Tasks - Start ------------
//--------------------------------------------------

gulp.task('management-js-libs', function () {
    var files = [
        './bower_components/angular/angular.min.js',
        './bower_components/angular-component-router/angular_1_router.js',
        './bower_components/angular-component-router/ng_route_shim.js'
    ];
    return gulp.src(files)
        .pipe(sourcemaps.init())
        .pipe(uglify())
        .pipe(concat('vendor.js'))
        .pipe(sourcemaps.write('./'))
        .pipe(gulp.dest('./dist/management/js'));
});

gulp.task('management-js', function () {
    var files = [
        'src/management/management.module.js',
        'src/management/**/*.js'
    ];
    return gulp.src(files)
        .pipe(sourcemaps.init())
        .pipe(embedTemplates())
        .pipe(concat('app.js'))
        .pipe(ngAnnotate())
        .pipe(uglify())
        .pipe(sourcemaps.write('./'))
        .pipe(gulp.dest('./dist/management/js'));
});

gulp.task('build-management', [
    'management-js-libs',
    'management-js'
]);

//--------------------------------------------------
//------------- Management Tasks - End -------------
//--------------------------------------------------


//--------------------------------------------------
//----------- Management v2 Tasks - Start ----------
//--------------------------------------------------

gulp.task('build-management-v2', function(callback) {
    webpack(webpackConfig, function(err, stats) {
        if(err) throw new gutil.PluginError("webpack", err);
        gutil.log("[webpack]", stats.toString({
            // output options
        }));
        callback();
    });
});

//--------------------------------------------------
//------------ Management v2 Tasks - End -----------
//--------------------------------------------------

gulp.task('build', [
    'build-shop',
    // Commented for build performance reasons
    // 'build-management',   // Angular 1 version
    // 'build-management-v2' // Angular 2 version
]);

gulp.task('watch', function () {
    gulp.watch('./src/framework/**/*.js', ['shop-js-framework']);
    gulp.watch('./src/app/**/*.js', ['shop-js']);
    gulp.watch('./src/app/**/*.css', ['shop-css']);
    gulp.watch('./src/management/**/*', ['management-js']);
});

gulp.task('default', ['build', 'watch']);
