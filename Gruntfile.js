module.exports = function(grunt) {
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),

        copy: { //make copy so we can compare results with Static's output
            dist: {
                cwd: 'html',
                expand: true,
                src: '**',
                dest: 'dist/'
            }
        },

        uncss: {
            dist: {
                files: [{
                    src: 'html/*.html',
                    dest: 'dist/css/compiled.css'
                }],
                options: {
                    compress: true
                }
            }
        },

        processhtml: {
            dist: {
                files: [{
                    expand: true,
                    cwd: 'html/',
                    src: ['**/*.html'],
                    dest: 'dist/',
                    ext: '.html'
                }]
            }
        },

       cssmin: {
            minify: {
                files: {
                    'dist/css/compiled.min.css': ['dist/css/compiled.css']
                }
            }
       },

        'http-server': {
            dev: {
                    // the server root directory
                    root: 'dist/',

                    port: 8282,
                    host: "127.0.0.1",

                    cache: 5,
                    showDir : true,
                    autoIndex: true,
                    defaultExt: "html",

                    //wait or not for the process to finish
                    runInBackground: false
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-uncss');
    grunt.loadNpmTasks('grunt-processhtml');
    grunt.loadNpmTasks('grunt-contrib-cssmin');

    grunt.loadNpmTasks('grunt-http-server');
    

    grunt.registerTask('default', ['copy', 'uncss','processhtml', 'cssmin']);
};
