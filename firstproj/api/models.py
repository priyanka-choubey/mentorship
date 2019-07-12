from django.db import models

# Create your models here.
from django.db import models



class Articles(models.Model):
    created = models.DateTimeField(auto_now_add=True)
    title = models.CharField(max_length=100, blank=True, default='')
    description = models.TextField()
    author = models.CharField(max_length=100, blank=True, default='')
    language = models.CharField( default='english', max_length=100)

    class Meta:
        ordering = ('created',)



class Mentor(models.Model):
    mentorname = models.CharField(max_length=100, default='koko')
    city = models.CharField(max_length=100, default='New Delhi')
    subject_of_expertise= models.TextField(default='Python')
    bio = models.TextField(default='programmer for life')
    phone = models.IntegerField(max_length=10,default='9090909090')
    email_id = models.CharField(max_length=200 , verbose_name="Parent's Email" ,default='pc@cc.com')
    experience = models.TextField(default='well experienced')
    question_solved = models.IntegerField(default=0)
    total_upvotes = models.IntegerField(default=0)

class Mentee(models.Model):
    menteename = models.CharField(max_length=100, blank=True, default='')
    city = models.CharField(max_length=100, blank=True, default='')
    bio = models.TextField()
    email_id = models.CharField(max_length=200 , verbose_name="Parent's Email" , null=True)
    phone = models.IntegerField(max_length=10)
    bio = models.TextField()
    subject_of_interest= models.TextField()
    mentor = models.ForeignKey(Mentor, on_delete=models.CASCADE,null=True)

class Question(models.Model):
    created = models.DateTimeField(auto_now_add=True)
    title = models.CharField(max_length=100, blank=True, default='')
    description = models.TextField()
    author = models.ForeignKey(Mentee, on_delete=models.CASCADE)
    language = models.CharField( default='english', max_length=100)
    upvotes = models.IntegerField(default=0)

    class Meta:
        ordering = ('upvotes',)

class Answer(models.Model):
    created = models.DateTimeField(auto_now_add=True)
    question = models.ForeignKey(Question, on_delete=models.CASCADE)
    title = models.CharField(max_length=100, blank=True, default='')
    description = models.TextField()
    author = models.ForeignKey(Mentor, on_delete=models.CASCADE)
    language = models.CharField( default='english', max_length=100)
    upvotes = models.IntegerField(default=0)

    class Meta:
        ordering = ('upvotes',)
