from rest_framework import serializers
from api.models import Articles,Question,Answer,Mentor,Mentee

class ArticlesSerializer(serializers.ModelSerializer):
    class Meta:
        model = Articles
        fields = ('id', 'title', 'description', 'author', 'language')

class QuestionSerializer(serializers.ModelSerializer):
    class Meta:
        model = Question
        fields = ('id', 'title', 'description','author','language','upvotes')

class AnswerSerializer(serializers.ModelSerializer):
    class Meta:
        model = Answer
        fields = ('id', 'title', 'description','author','language','upvotes')

class MentorSerializer(serializers.ModelSerializer):
    class Meta:
        model = Mentor
        fields = ('id', 'mentorname', 'city','subject_of_expertise','email_id','phone','bio','experience')

class MenteeSerializer(serializers.ModelSerializer):
    class Meta:
        model = Mentee
        fields = ('id', 'menteename', 'city','subject_of_interest','email_id','phone','bio')
