from fastapi import FastAPI
from pydantic import BaseModel
import requests
from config import LLM_API_URL, LLM_API_KEY, LLM_MODEL

app = FastAPI()

class ReviewRequest(BaseModel):
    code: str

@app.post("/review")
def review_code(request: ReviewRequest):
    headers = {"Authorization": f"Bearer {LLM_API_KEY}"} if LLM_API_KEY else {}
    payload = {
        "model": LLM_MODEL,
        "messages": [{"role": "user", "content": f"Review this code: {request.code}"}]
    }
    response = requests.post(LLM_API_URL, json=payload, headers=headers)
    if response.ok:
        return {"review": response.json()["choices"][0]["message"]["content"]}
    else:
        return {"error": response.text}