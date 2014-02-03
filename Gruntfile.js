module.exports = function(grunt) {
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),

        copy: {
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
        }
    });

    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-uncss');
    grunt.loadNpmTasks('grunt-processhtml');

    grunt.registerTask('default', ['copy', 'uncss','processhtml']);
};
