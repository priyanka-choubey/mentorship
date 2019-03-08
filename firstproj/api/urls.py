from django.conf.urls import url
from django.conf.urls import include
from api.views import get_question,add_answer,add_question,add_mentee,add_mentor,get_questions,get_answers,get_mentees,get_mentors

urlpatterns = [

    url(r'mentor/$', add_mentor),
    url(r'question/(?P<user_id>\d+)/$', get_question),
    url(r'questions/$', get_questions),
    url(r'question/(?P<user_id>\d+)/answers/$', get_answers),
    url(r'mentee/$', add_mentee),
    url(r'mentors/$', get_mentors),
    url(r'mentees/$', get_mentees),
    url(r'mentees/(?P<user_id>\d+)/question/$', add_question),
    url(r'mentors/(?P<user_id>\d+)/questions/(?P<question_id>\d+)/answer/$', add_answer),
]
