from django.conf.urls import url
from django.conf.urls import include
from api.views import get_question,add_answer,add_question,add_mentee,add_mentor,get_questions,get_answers,get_mentees,get_mentors,get_mentor,link_mentor,get_mentor_by_name,get_mentee_by_name

urlpatterns = [

    url(r'mentor/$', add_mentor),
    url(r'question/(?P<user_id>\d+)/$', get_question),
    url(r'questions/$', get_questions),
    url(r'question/(?P<user_id>\d+)/answers/$', get_answers),
    url(r'mentee/$', add_mentee),
    url(r'mentors/$', get_mentors),
    url(r'mentees/$', get_mentees),
    url(r'mentor/(?P<name>\w+)/$', get_mentor_by_name),
    url(r'mentee/(?P<name>\w+)/$', get_mentee_by_name),
    url(r'mentee/(?P<user_id>\d+)/$', get_mentor),
    url(r'mentees/(?P<user_id>\d+)/question/$', add_question),
    url(r'mentors/(?P<user_id>\d+)/questions/(?P<question_id>\d+)/answer/$', add_answer),
    url(r'mentors/(?P<mentor_id>\d+)/mentees/(?P<mentee_id>\d+)/$', link_mentor),
]
