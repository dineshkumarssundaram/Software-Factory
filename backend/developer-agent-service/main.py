from fastapi import FastAPI
from pydantic import BaseModel
import requests
from config import LLM_API_URL, LLM_API_KEY, LLM_MODEL

app = FastAPI()

class GenerateRequest(BaseModel):
    prompt: str

@app.post("/generate")
def generate_code(request: GenerateRequest):
    headers = {"Authorization": f"Bearer {LLM_API_KEY}"} if LLM_API_KEY else {}
    payload = {
        "model": LLM_MODEL,
        "messages": [{"role": "user", "content": request.prompt}]
    }
    response = requests.post(LLM_API_URL, json=payload, headers=headers)
    if response.ok:
        return {"code": response.json()["choices"][0]["message"]["content"]}
    else:
        return {"error": response.text}