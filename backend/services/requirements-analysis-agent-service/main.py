from fastapi import FastAPI
from pydantic import BaseModel
import requests
from config import LLM_API_URL, LLM_API_KEY, LLM_MODEL

app = FastAPI()

class AnalyzeRequest(BaseModel):
    requirements: str

@app.post("/analyze")
def analyze_requirements(request: AnalyzeRequest):
    headers = {"Authorization": f"Bearer {LLM_API_KEY}"} if LLM_API_KEY else {}
    payload = {
        "model": LLM_MODEL,
        "messages": [{"role": "user", "content": f"Analyze these requirements: {request.requirements}"}]
    }
    response = requests.post(LLM_API_URL, json=payload, headers=headers)
    if response.ok:
        return {"analysis": response.json()["choices"][0]["message"]["content"]}
    else:
        return {"error": response.text}