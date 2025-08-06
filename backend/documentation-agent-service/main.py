from fastapi import FastAPI
from pydantic import BaseModel
import requests
from config import LLM_API_URL, LLM_API_KEY, LLM_MODEL

app = FastAPI()

class DocumentRequest(BaseModel):
    code: str

@app.post("/document")
def document_code(request: DocumentRequest):
    headers = {"Authorization": f"Bearer {LLM_API_KEY}"} if LLM_API_KEY else {}
    payload = {
        "model": LLM_MODEL,
        "messages": [{"role": "user", "content": f"Document this code: {request.code}"}]
    }
    response = requests.post(LLM_API_URL, json=payload, headers=headers)
    if response.ok:
        return {"documentation": response.json()["choices"][0]["message"]["content"]}
    else:
        return {"error": response.text}