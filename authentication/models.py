import hashlib
from urllib.parse import urlencode
from django.contrib.auth.models import User
from django.db import models
from django.db.models.signals import post_save
from django.dispatch import receiver
from .choices import GENDER_CHOICES,ROLE_CHOICES,EDUCATION_CHOICES, MENTORSHIP_AREAS_CHOICES
from multiselectfield import MultiSelectField
from imagekit.models import ProcessedImageField
from imagekit.processors import ResizeToFill

DEFAULT = 'profiles/default.jpg'

class Profile(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    location = models.CharField(max_length=50, default='')
    bio = models.TextField(default='')
    gender = models.CharField(
        max_length=6,
        choices=GENDER_CHOICES,
        default=''
        )
    role = models.CharField(
        max_length=6,
        blank=False,
        default='mentee',
        choices=ROLE_CHOICES
        )
    phone_number = models.CharField(max_length=32, default='')
    mentorship_areas = MultiSelectField(
        choices=MENTORSHIP_AREAS_CHOICES,
        default=''
        )
    highest_level_of_study = models.CharField(
        max_length=255,
        choices=EDUCATION_CHOICES,
        default=''
        )
    is_previously_logged_in = models.CharField(max_length=5, default=False)
    email_confirmed = models.BooleanField(default=False)
    profile_picture = ProcessedImageField(
        upload_to='profiles',
        processors=[ResizeToFill(300, 300)],
        format='JPEG',
        options={'quality': 99},
        default=DEFAULT,
        )

    def is_mentor(self):
        return self.role == 'mentor'

    def is_mentee(self):
        return self.role == 'mentee'

    class Meta:
        db_table = 'auth_profile'

    def __str__(self):
        return self.user.username

    def get_screen_name(self):
        if self.user.get_full_name():
            return self.user.get_full_name()
        else:
            return self.user.username

@receiver(post_save, sender=User)
def update_user_profile(sender, instance, created, **kwargs):
    if created:
        Profile.objects.create(user=instance)
    instance.profile.save()


class UserCode(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    code = models.CharField(max_length=6)


class Interest(models.Model):
    user = models.OneToOneField(
        User,
        blank = True, on_delete = models.CASCADE)
    stem = models.BooleanField(blank = True, default = False)
    entrepreneurship = models.BooleanField(blank = True, default = False)
    career_counseling = models.BooleanField(blank = True, default = False)
    career_readiness = models.BooleanField(
        db_column = "career_readiness",
        verbose_name = 'career_readiness',
        blank = True, default = False)
    addictions = models.BooleanField(blank = True, default = False)

    class Meta:
        db_table = "interests"

    def __unicode__(self):
        return unicode(self.user.username)


class Connection(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    mentor = models.IntegerField(null=False)
    status = models.IntegerField(null=False, default=0)

    def __unicode__(self):
        return unicode(self.user)
