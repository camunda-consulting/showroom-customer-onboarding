# copy-paste the credentials downloaded from Console while credential creation:

export ZEEBE_ADDRESS=
export ZEEBE_CLIENT_ID=
export ZEEBE_CLIENT_SECRET=
export ZEEBE_AUTHORIZATION_SERVER_URL=

# # Optional: Uncomment and adjust (look at ZEEBE_ADDRESS) if you want to use INT stage:
# export ZEEBE_CLIENT_CLOUD_REGION='bru-3'
# export ZEEBE_CLIENT_CLOUD_BASEURL=zeebe.ultrawombat.com
# export ZEEBE_CLIENT_CLOUD_PORT=443
# # Optional: Uncomment and adjust (look at ZEEBE_ADDRESS) if you want to use DEV stage:
# export ZEEBE_CLIENT_CLOUD_REGION='bru-2'
# export ZEEBE_CLIENT_CLOUD_BASEURL=zeebe.dev.ultrawombat.com
# export ZEEBE_CLIENT_CLOUD_PORT=443

###
# Done. From here the data is massaged to be accessible by other clients/tools
###

export ZEEBE_CLIENT_CLOUD_CLUSTERID="${ZEEBE_ADDRESS:0:36}"
export ZEEBE_CLIENT_CLOUD_CLIENTID="${ZEEBE_CLIENT_ID}"
export ZEEBE_CLIENT_CLOUD_CLIENTSECRET="${ZEEBE_CLIENT_SECRET}"
export ZEEBE_CLIENT_CLOUD_AUTHURL="${ZEEBE_AUTHORIZATION_SERVER_URL}"
