# -*- coding: utf-8 -*-
# Generated by Django 1.10.4 on 2017-11-29 05:08
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('questions', '0002_question_category'),
    ]

    operations = [
        migrations.AlterField(
            model_name='question',
            name='category',
            field=models.CharField(choices=[('academic_assistance', 'Academic Assistance'), ('entrepreneurship', 'Entrepreneurship'), ('career_counseling', 'Career Counseling'), ('career_readiness', 'Career readiness (Interviews or Job hunting)'), ('soft_skills', 'Soft Skills'), ('naturing_talent', 'Naturing Talent'), ('public_speaking', 'Public Speaking'), ('film_production', 'Film Production'), ('technology', 'Technology'), ('computer_programming', 'Computer Programming'), ('grief_loss', 'Coping with grief and loss'), ('addictions', 'Coping with addictions'), ('abuse', 'Trauma and Abuse'), ('no_preference', 'Prefer not to answer')], default='', max_length=255),
        ),
    ]
