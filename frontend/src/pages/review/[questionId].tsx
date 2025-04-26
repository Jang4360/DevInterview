"use client";

import React, { useState, useEffect } from "react";
import { useRouter, useSearchParams } from "next/navigation";
import Button from "@/components/common/Button";
import axios from "axios";

const dummyQna = {
  id: 1,
  question:
    "마이크로서비스 혹은 분산 시스템에서 데이터 일관성을 유지하기 위한 전략을 고민하거나 적용한 경험이 있다면 공유해주세요.",
  answer:
    "마이크로서비스에서는 Saga 패턴이나 Eventual Consistency를 고려할 수 있습니다.",
};

export default function ReviewDetailPage() {
  const [userAnswer, setUserAnswer] = useState("");
  const [showAnswer, setShowAnswer] = useState(false);
  const searchParams = useSearchParams();
  const router = useRouter();

  const fromSidebar = searchParams.get("fromSidebar") === "true";
  const fromCreate = searchParams.get("fromCreate") === "true";

  // 🔥 Create 버튼 숨기기
  useEffect(() => {
    const createBtn = document.getElementById("create-btn");
    if (fromCreate || !fromSidebar) {
      if (createBtn) createBtn.style.display = "none";
    } else {
      if (createBtn) createBtn.style.display = "block";
    }
  }, [fromSidebar, fromCreate]);

  // 복습 완료 핸들러
  const handleCompleteReview = async () => {
    try {
      await axios.post("/api/review", {
        userId: "현재_로그인_유저_ID", // 실제 로그인 유저 ID 필요
        qnaId: dummyQna.id,
      });
      alert("복습 완료되었습니다.");
      setTimeout(() => {
        router.push("/review");
      }, 1000);
    } catch (error) {
      alert("복습 처리 실패");
    }
  };

  return (
    <div className="p-6 text-white">
      {/* 질문 */}
      <div className="bg-[#303030] p-4 rounded mb-4">
        <h3 className="font-bold text-lg mb-2">Question</h3>
        <p>{dummyQna.question}</p>
      </div>

      {/* 답변 작성 */}
      {fromSidebar && (
        <textarea
          value={userAnswer}
          onChange={(e) => setUserAnswer(e.target.value)}
          placeholder="답변을 작성해 보세요"
          className="w-full h-40 p-4 bg-[#303030] text-white rounded mb-4 placeholder-[#BBB5B5]"
        />
      )}

      {/* 모범 답변 확인 */}
      <div className="bg-[#303030] p-4 rounded mb-4 flex justify-between items-center">
        <h3 className="font-bold">Answer</h3>
        <Button onClick={() => setShowAnswer(!showAnswer)} className="ml-2">
          확인하기 &gt;
        </Button>
      </div>
      {showAnswer && (
        <div className="p-4 bg-[#303030] rounded mb-4">
          <p>{dummyQna.answer}</p>
        </div>
      )}

      {/* 복습 완료 버튼 (사이드바에서 온 경우만 표시) */}
      {fromSidebar && (
        <div className="flex justify-center">
          <Button
            onClick={handleCompleteReview}
            className="border border-white text-white px-6"
          >
            복습 완료
          </Button>
        </div>
      )}
    </div>
  );
}
