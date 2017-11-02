'use strict';

var path = require('path');
var gulp = require('gulp');
var conf = require('./conf');

gulp.task('copyVendorImages', function () {
  return gulp
    .src([      
      // Include images from vendor 
      // For example: amchart
      // path.join(conf.wiredep.directory, '**/amcharts/dist/amcharts/images/**/*'),      
    ])
    .pipe(gulp.dest(path.join(conf.paths.tmp, 'serve', '/assets/img/theme/vendor')));
});

