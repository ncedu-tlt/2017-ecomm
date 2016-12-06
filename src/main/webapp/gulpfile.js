var gulp = require('gulp');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var sourcemaps = require('gulp-sourcemaps');
var autoprefixer = require('gulp-autoprefixer');

gulp.task('js-libs', function () {
    var files = [
        './bower_components/jquery/dist/jquery.min.js',
        './bower_components/semantic/dist/semantic.js'
    ];
    return gulp.src(files)
        .pipe(sourcemaps.init())
        .pipe(uglify())
        .pipe(concat('vendor.js'))
        .pipe(sourcemaps.write('./'))
        .pipe(gulp.dest('./dist/js'))
});

gulp.task('js-framework', function () {
    var files = [
        'src/framework/**/*.js'
    ];
    return gulp.src(files)
        .pipe(sourcemaps.init())
        .pipe(concat('framework.js'))
        .pipe(uglify())
        .pipe(sourcemaps.write('./'))
        .pipe(gulp.dest('./dist/js'))
});

gulp.task('js', function () {
    var files = [
        'src/app/**/*.js'
    ];
    return gulp.src(files)
        .pipe(sourcemaps.init())
        .pipe(concat('app.js'))
        .pipe(uglify())
        .pipe(sourcemaps.write('./'))
        .pipe(gulp.dest('./dist/js'))
});

gulp.task('copy-themes', function () {
    var files = [
        './bower_components/semantic/dist/themes/default/**/*'
    ];
    return gulp.src(files)
        .pipe(gulp.dest('./dist/css/themes/default'));
});

gulp.task('css-libs', ['copy-themes'], function () {
    var files = [
        './bower_components/semantic/dist/semantic.css'
    ];
    return gulp.src(files)
        .pipe(sourcemaps.init())
        .pipe(concat('vendor.css'))
        .pipe(sourcemaps.write('./'))
        .pipe(gulp.dest('./dist/css'));
});

gulp.task('css', function () {
    var files = [
        'src/app/**/*.css'
    ];
    return gulp.src(files)
        .pipe(sourcemaps.init())
        .pipe(concat('app.css'))
        .pipe(autoprefixer())
        .pipe(sourcemaps.write('./'))
        .pipe(gulp.dest('./dist/css'));
});

gulp.task('build', ['js-libs', 'js-framework', 'js', 'css-libs', 'css']);

gulp.task('watch', function () {
    gulp.watch('./src/framework/**/*.js', ['js-framework']);
    gulp.watch('./src/app/**/*.js', ['js']);
    gulp.watch('./src/app/**/*.css', ['css']);
});

gulp.task('default', ['build', 'watch']);
