---
title: Quick API Prototypes with Node.js and Express
tags: JavaScript NodeJS API
---

You have an idea and want to stub out a quick REST API to test it out. So you reach for C++ and -- haha, of course you don't because that would be crazy (I see you over there, Java, and no, back into the corner you go). Node.js is my goto solution in situations like this.

A few days ago I was doing some early Spring cleaning and found some old code from when I was first experimenting with [Backbone.js](http://backbonejs.org/). Backbone's [sync](http://backbonejs.org/#Sync) makes it super easy to persist data; you might not even have to configure routes if your API is already RESTful.  I, however, did not have a RESTful API.  I had a silly little kanban style todo list running in my browser. Node.js to the rescue!

Node is great.  But out of the box, manipulating HTTP requests and responses is a little less abstract than we want for prototyping an API.  That's where [Express](http://expressjs.com/) comes in.  Express is a web application framework for Node, and it makes set up routes a piece of cake.

First, (and assuming you already have Node installed), let's install Express:

```prettyprint
npm install express --save
```

If you already have a `package.json` file in your project directory, the `--save` flag will add Express to your dependencies.

Now let's make a simple REST API for some to-do items.  Since this is just for testing purposes, we'll use an array instead of a fancy database to hold our data. Note that the following example is extremely crude, and is totally lacking in any sort of error checking.  However, I think it shows how quickly you can get a somewhat useful "fake" backend up and running.

```prettyprint

var express = require('express'),
app = express(),
PORT = 8008;
//array of tasks
var tasks = [{id: 0, title: "Learn more about Backbone", description:"Do research on best practices", col: "inProgress"},
{id: 1, title: "Get Drag and Drop to work", col:"completed"},
{id: 2, title: "Experiment with local storage for tasks", col: "completed"}];

/*
 * REST TIME!!
 */
//Get some JSON
app.get('/api/tasks', function(req, res){
        console.log('GET /api/tasks');
        console.log(tasks);
        res.send(tasks);
});
//get a particular task
app.get('/api/tasks/:id', function(req,res){
        var id = req.params.id;
        console.log('getting task ' + id);
        res.send(tasks[id]);
});
//add a task
app.post('/api/tasks', function(req,res){
        var task = req.body;
        task.id = tasks.length;
        console.log(req.body);
        console.log('adding task: ' + task);
        tasks.push(task);
        console.log(tasks);
});

//update a task
app.put('/api/tasks/:id', function(req,res){
        var id = req.params.id;
        var task = req.body;
        console.log('updating task ' + id + ' to ' + JSON.stringify(task));
        tasks[id] = task;
        console.log(tasks);
});
app.listen(PORT);
console.log('Listening on port ' + PORT);

```
