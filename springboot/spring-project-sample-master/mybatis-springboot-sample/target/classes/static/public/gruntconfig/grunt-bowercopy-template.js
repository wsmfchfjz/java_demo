grunt.initConfig({
    bowercopy: {
        options: {
            // Bower components folder will be removed afterwards 
            clean: true
        },
        // Anything can be copied 
        test: {
            options: {
                destPrefix: 'test/js'
            },
            files: {
                // Keys are destinations (prefixed with `options.destPrefix`) 
                // Values are sources (prefixed with `options.srcPrefix`); One source per destination 
                // e.g. 'bower_components/chai/lib/chai.js' will be copied to 'test/js/libs/chai.js' 
                'libs/chai.js': 'chai/lib/chai.js',
                'mocha/mocha.js': 'libs/mocha/mocha.js',
                'mocha/mocha.css': 'libs/mocha/mocha.css'
            }
        },
        // Javascript 
        libs: {
            options: {
                destPrefix: 'public/js/libs'
            },
            files: {
                'jquery.js': 'jquery/jquery.js',
                'require.js': 'requirejs/require.js'
            },
        },
        plugins: {
            options: {
                destPrefix: 'public/js/plugins'
            },
            files: {
                // Make dependencies follow your naming conventions 
                'jquery.chosen.js': 'chosen/public/chosen.js'
            }
        },
        // Less 
        less: {
            options: {
                destPrefix: 'less'
            },
            files: {
                // If either the src or the dest is not present, 
                // the specified location will be used for both. 
                // In other words, this will copy 
                // 'bower_components/bootstrap/less/dropdowns.less' to 'less/bootstrap/less/dropdowns.less' 
                // See http://gruntjs.com/configuring-tasks#files for recommended files formats 
                src: 'bootstrap/less/dropdowns.less'
            }
        },
        // Images 
        images: {
            options: {
                destPrefix: 'public/images'
            },
            files: {
                'account/chosen-sprite.png': 'chosen/public/chosen-sprite.png',
                'account/chosen-sprite@2x.png': 'chosen/public/chosen-sprite@2x.png'
            }
        },
        // Entire folders 
        folders: {
            files: {
                // Note: when copying folders, the destination (key) will be used as the location for the folder 
                'public/js/libs/lodash': 'lodash',
                // The destination can also be a folder 
                // Note: if the basename of the location does not have a period('.'), 
                // it is assumed that you'd like a folder to be created if none exists 
                // and the source filename will be used 
                'public/js/libs': 'lodash/dist/lodash.js'
            }
        },
        // Glob patterns 
        glob: {
            files: {
                // When using glob patterns, destinations are *always* folder names 
                // into which matching files will be copied 
                // Also note that subdirectories are **not** maintained 
                // if a destination is specified 
                // For example, one of the files copied here is 
                // 'lodash/dist/lodash.js' -> 'public/js/libs/lodash/lodash.js' 
                'public/js/libs/lodash': 'lodash/dist/*.js'
            }
        },
        // Glob without destination 
        globSrc: {
            options: {
                destPrefix: 'public/js/libs'
            },
            // By not specifying a destination, you are denoting 
            // that the lodash directory structure should be maintained 
            // when copying. 
            // For example, one of the files copied here is 
            // 'lodash/dist/lodash.js' -> 'public/js/libs/lodash/dist/lodash.js' 
            src: 'lodash/**/*.js'
        },
        // Main pragma 
        // Adding :main to the end of a source path will retrieve the main file(s) for that package 
        // If the main property is not specified by a package, bowercopy will fail 
        main: {
            src: 'jquery.minlight:main',
            dest: 'public/js/plugins/'
        }
    }
});