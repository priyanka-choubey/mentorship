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
    mentorname = models.CharField(max_length=100, blank=True, default='')
    city = models.CharField(max_length=100, blank=True, default='')
    subject_of_expertise= models.TextField()
    bio = models.TextField()
    phone = models.IntegerField(max_length=10)
    email_id = models.CharField(max_length=200 , verbose_name="Parent's Email" , null=True)
    experience = models.TextField()
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
