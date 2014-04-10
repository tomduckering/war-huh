#!/bin/bash

crowd_url="http://www.atlassian.com/software/crowd/downloads/binary/atlassian-crowd-2.6.2-war.zip"
crowd_openid_url="http://www.atlassian.com/software/crowd/downloads/binary/atlassian-crowd-openid-2.6.2-war.zip"

test -f crowd.war        || curl --silent --location --output crowd.war        ${crowd_url}
test -f crowd-openid.war || curl --silent --location --output crowd-openid.war ${crowd_openid_url}