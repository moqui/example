
# Example Markdown Page

This is an example wiki page using the Markdown style of wiki markup (hence the extension md or markdown).

References for the syntax are available on these pages:

[John Gruber Markdown Syntax](http://daringfireball.net/projects/markdown/syntax)
[PegDown Project](https://github.com/sirthias/pegdown)

If you are reading this in your browser you are probably viewing this in an HTML form which was created by transforming
the Markdown text to HTML using the Markdown4J library.

* Bullet 1
    * Bullet 1.1
    * Bullet 1.2
* Bullet 2
    1. Bullet 2 Number 1
    1. Bullet 2 Number 2

## Sub-Heading 1

This is the text under Sub-Heading 1.

## Sub-Heading 2

This is the text under Sub-Heading 2.

# FTL Markup Supported

This content is also dynamic and the normal context is available here.

For example, here is your userId: ${ec.user.userId!"(No User Logged In)"}
