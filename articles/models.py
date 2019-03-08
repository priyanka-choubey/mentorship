from __future__ import unicode_literals

from django.contrib.auth.models import User
from django.db import models
from autoslug import AutoSlugField
from django.utils.translation import ugettext_lazy as _
import markdown

class Article(models.Model):
    draft = 'D'
    published = 'P'
    status = (
        (draft, 'Draft'),
        (published, 'Published'),
    )

    title = models.CharField(max_length=255)
    slug = AutoSlugField(populate_from='title')
    content = models.TextField(max_length=4000)
    status = models.CharField(max_length=1, choices=status, default=draft)
    create_user = models.ForeignKey(User)
    create_date = models.DateTimeField(auto_now_add=True)
    update_date = models.DateTimeField(auto_now=True)
    update_user = models.ForeignKey(User, null=True, blank=True,
                                    related_name="+")

    class Meta:
        verbose_name = _("Article")
        verbose_name_plural = _("Articles")
        ordering = ("-create_date",)

    def __str__(self):
        return self.title

    @staticmethod
    def get_published():
        articles = Article.objects.filter(status=Article.PUBLISHED)
        return articles

    def get_summary(self):
        if len(self.content) > 255:
            return '{0}...'.format(self.content[:255])
        else:
            return self.content

    def get_comments(self):
        return ArticleComment.objects.filter(article=self)

class ArticleComment(models.Model):
    article = models.ForeignKey(Article)
    comment = models.CharField(max_length=500)
    date = models.DateTimeField(auto_now_add=True)
    user = models.ForeignKey(User)

    class Meta:
        verbose_name = _("Article Comment")
        verbose_name_plural = _("Article Comments")
        ordering = ("date",)

    def __str__(self):
        return '{0} - {1}'.format(self.user.username, self.article.title)
