from django.shortcuts import render
from api.models import Mentor,Mentee,Question,Answer,Articles
from api.serializers import QuestionSerializer,AnswerSerializer,MentorSerializer,MenteeSerializer
from rest_framework.response import Response
from rest_framework.decorators import api_view
from rest_framework import status

# Create your views here.
@api_view(['GET'])
def get_question(request, question_id):
    try:
        question = Question.objects.get(id=question_id)
        serializer = QuestionSerializer(question)
        return Response(serializer.data, status = status.HTTP_200_OK)
    except Exception as e:
        return Response({}, status = status.status.HTTP_400_BAD_REQUEST)

@api_view(['GET'])
def get_answers(request, question_id):
    try:
        question = Question.objects.get(id=question_id)
        answers= Answer.objects.filter(question=question)
        serializer = AnswerSerializer(answers)
        return Response(serializer.data, status = status.HTTP_200_OK)
    except Exception as e:
        return Response({}, status = status.status.HTTP_400_BAD_REQUEST)

@api_view(['GET'])
def get_questions(request):
    try:
        questions = Question.objects.all()
        serializer = QuestionSerializer(questions)
        return Response(serializer.data, status = status.HTTP_200_OK)
    except Exception as e:
        return Response({}, status = status.status.HTTP_400_BAD_REQUEST)

@api_view(['GET'])
def get_mentors(request):
    try:
        mentors = Mentor.objects.all()
        serializer = MentorSerializer(mentors)
        return Response(serializer.data, status = status.HTTP_200_OK)
    except Exception as e:
        return Response({}, status = status.status.HTTP_400_BAD_REQUEST)

@api_view(['GET'])
def get_mentees(request):
    try:
        mentees = Mentee.objects.all
        serializer = MenteeSerializer(mentees)
        return Response(serializer.data, status = status.HTTP_200_OK)
    except Exception as e:
        return Response({}, status = status.status.HTTP_400_BAD_REQUEST)

@api_view(['POST'])
def add_mentor(request):

        mentor = Mentor()
        mentor.mentorname =request.data['mentorname']
        mentor.city = request.data['city']
        mentor.bio = request.data['bio']
        mentor.phone = request.data['phone']
        mentor.email_id = request.data['email_id']
        mentor.subject_of_expertise = request.data['subject_of_expertise']
        mentor.experience = request.data['experience']
        mentor.save()
        return Response(MentorSerializer(mentor).data, status = status.HTTP_201_CREATED)


@api_view(['POST'])
def add_mentee(request):

        mentee = Mentee()
        mentee.menteename =request.data['menteename']
        mentee.city = request.data['city']
        mentee.bio = request.data['bio']
        mentee.phone = request.data['phone']
        mentee.email_id = request.data['email_id']
        mentee.subject_of_interest = request.data['subject_of_interest']
        mentee.save()
        return Response(MenteeSerializer(mentee).data, status = status.HTTP_201_CREATED)


@api_view(['POST'])
def add_question(request, user_id):

        mentee = Mentee.objects.get(id=user_id)
        question = Question()
        question.author = mentee
        question.title = request.data['title']
        question.description = request.data['description']
        question.language = request.data['language']
        question.save()
        return Response(QuestionSerializer(question).data, status = status.HTTP_201_CREATED)


@api_view(['POST'])
def add_answer(request, user_id, question_id):

        mentor = Mentor.objects.get(id=user_id)
        question = Question.objects.get(id=question_id)
        answer = Answer()
        answer.author = mentor
        answer.question=question
        answer.title = request.data['title']
        answer.description = request.data['description']
        answer.language = request.data['language']
        answer.save()
        return Response(AnswerSerializer(answer).data, status = status.HTTP_201_CREATED)

@api_view(['GET'])
def get_mentor(request, user_id):

        mentee=Mentee.objects.get(id=user_id)
        mentor =mentee.mentor
        serializer = MentorSerializer(mentor)
        return Response(serializer.data, status = status.HTTP_200_OK)

@api_view(['GET'])
def get_mentor_by_name(request, name):

        mentor=Mentor.objects.get(mentorname=name)
        serializer = MentorSerializer(mentor)
        return Response(serializer.data, status = status.HTTP_200_OK)


@api_view(['GET'])
def get_mentee_by_name(request, name):

        mentee=Mentee.objects.get(menteename=name)
        serializer = MenteeSerializer(mentee)
        return Response(serializer.data, status = status.HTTP_200_OK)

@api_view(['GET'])
def link_mentor(request, mentor_id, mentee_id):

        mentee = Mentee.objects.get(id=mentee_id)
        mentee.mentor = Mentor.objects.get(id = mentor_id)
        return Response(MenteeSerializer(mentee).data, status = status.HTTP_201_CREATED)
