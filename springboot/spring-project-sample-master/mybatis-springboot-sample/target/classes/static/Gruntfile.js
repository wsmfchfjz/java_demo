module.exports = function (grunt) {

    // Project configuration.
    grunt.initConfig({
        bowercopy: {
            options: {
                clean: false
            },
            libs: {
                options: {
                    destPrefix: 'public/js/libs'
                },
                files: {
                    'bootstrap': 'bootstrap/dist/js',
                    'jquery': 'jquery/dist',
                    'knockout': 'knockout/dist/',
                    'require': 'requirejs',

                    'vue': 'vue/dist/'
                }
            },
            folders: {
                options: {
                    //srcPrefix: 'bower_components', //default
                    destPrefix: 'public'
                },
                files: {
                    'css/libs/': 'bootstrap/dist/css/',
                    'fonts/libs/': 'bootstrap/dist/fonts/'
                }
            }
        }
    });

    // 加载包含 "uglify" 任务的插件。
    grunt.loadNpmTasks('grunt-bowercopy');

    // 默认被执行的任务列表。
    grunt.registerTask('default', ['bowercopy']);

};