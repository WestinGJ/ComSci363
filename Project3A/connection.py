#!pip install pymongo # please comment this line before submission
# import all libs (do not change)
from pymongo import MongoClient
from pymongo.mongo_client import MongoClient
from pymongo.server_api import ServerApi
import json
import pymongo
import pprint
import nbformat
from nbconvert.preprocessors import ExecutePreprocessor
# fill in uri (5pts)
uri = mongodb+srv://westing:ComSci363@cluster0.neybl.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0
# Create a new client and connect to the server
client = MongoClient(uri, server_api=ServerApi('1'))
# Send a ping to confirm a successful connection
try:
    capture = client.admin.command('ping')
    print("Pinged your deployment. You successfully connected to MongoDB!", capture)
except Exception as e:
    print(e)