#! /bin/bash

# deploy the contents of $outout_dir to $repo 

# output_dir="html"
# output_dir="dist" # if using grunt's output
commit_message="Site updated on `date`"
repo="https://github.com/hlatki/hlatki.github.io.git"

cd $output_dir
git init 
git add .
git commit -m "$commit_message"
git remote add origin $repo
#git push origin master --force
