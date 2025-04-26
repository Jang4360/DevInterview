"use client";

import React, { useState } from "react";
import { useRouter } from "next/navigation";
import Input from "@/components/common/Input";
import Button from "@/components/common/Button";

export default function CreatePage() {
  const [content, setContent] = useState("");
  const router = useRouter();

  const handleGenerateQnA = () => {
    // 임시로 Spinner 페이지로 이동 (추후 content 값도 넘길 수 있음)
    router.push("/spinner");
  };

  return (
    <div className="p-6 text-white">
      <h2 className="text-xl font-bold mb-4">글 작성</h2>
      <textarea
        value={content}
        onChange={(e) => setContent(e.target.value)}
        placeholder="내용을 입력하세요"
        className="w-full h-80 p-4 bg-[#303030] text-white rounded mb-4 placeholder-[#BBB5B5]"
      />
      <div className="flex justify-center">
        <Button onClick={handleGenerateQnA} className="px-6 py-3">
          인터뷰 질문 생성하기
        </Button>
      </div>
    </div>
  );
}
