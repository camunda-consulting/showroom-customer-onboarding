# copy-paste the credentials downloaded from Console while credential creation:

export ZEEBE_ADDRESS='b1c5e41e-ccdf-49d9-bffa-976b890d4598.bru-2.zeebe.camunda.io:443'
export ZEEBE_CLIENT_ID=Whel5t.7eKf-_lKck3ZarY4iRrrWR2Ts
export ZEEBE_CLIENT_SECRET=LtXkQuQQ2T70.C_WzfEv9YxNAuFG7mNsV3uuaWWzHaEQ~d~pVaQJNcsb-7pdnxtA
export ZEEBE_AUTHORIZATION_SERVER_URL=https://login.cloud.camunda.io/oauth/token

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
