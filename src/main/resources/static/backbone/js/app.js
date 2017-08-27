$.fn.serializeObject = function(){
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};
/*
$.ajaxPrefilter(function(options, originalOptions, jqXHR){    
    var url = window.location.host;
    
    options.url = url + options.url; 
});
*/
var Users = Backbone.Collection.extend({
    url: '/api/users'
});

var User = Backbone.Model.extend({
    urlRoot: '/api/users'
});


var UserListPage = Backbone.View.extend({
    el:'.page',
    render: function(){
        var page = this.$el;        
        var users = new Users();
            users.fetch({
                success: function(users){
                    var template = _.template($('#user-list-template').html(), {'users': users.models});
                    page.html(template);
                },
                error:function(){
                    page.html("Error");
                }
            });
            
        this.$el.html("CONTENT SHOULD SHOW HERE!");
    } 
}); 

var EditUserPage = Backbone.View.extend({
    el:'.page',
    render: function(options){
        var page = this.$el;
        if (options.id){
            this.user = new User({'id':options.id});
            this.user.fetch({
                success:function(user){
                    var template = _.template($('#edit-user-template').html(), {'user':user});
                    page.html(template);                    
                }
            });    
        } else {                
            var template = _.template($('#edit-user-template').html(), {'user':null});
            page.html(template);
        }
        
    },
    events:{
        'submit .edit-user-form':'saveUser',
        'click .delete' : 'deleteUser'
    },
    deleteUser: function(ev){
        this.user.destroy({
            success:function(){
                router.navigate('',{trigger:true});
            }
        }); 
        return false;
    },
    saveUser: function(ev){
        var userDetails = $(ev.currentTarget).serializeObject();
    
        var user = new User();  
            user.save(userDetails,{ 
                      success:function(user){
                          console.log(user);
                          router.navigate('',{trigger:true});
                      }
            });
            
        //console.log(userDetails);        
        return false;   
    }
});

var userListPage = new UserListPage();
var editUserPage = new EditUserPage();

var Router = Backbone.Router.extend({
    routes: {
              '':'home',
           'new':'editUser',
      'edit/:id':'editUser',           
    }
}); 

var router = new Router();
    router.on('route:home', function(){
        userListPage.render();
    }).on('route:editUser', function(id){
        editUserPage.render({'id':id});
    });

Backbone.history.start();
