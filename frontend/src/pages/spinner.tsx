"use client";

import { useEffect } from "react";
import { useRouter } from "next/router"; // next/navigation → router.push 수정됨
import Spinner from "@/components/common/Spinner";

export default function SpinnerPage() {
  const router = useRouter();

  useEffect(() => {
    // 2초 후 QnA 상세 페이지로 이동 (더미 ID 기준)
    setTimeout(() => {
      router.push("/review/99"); // 생성된 QnA ID로 이동 예정
    }, 2000);
  }, [router]);

  return <Spinner />;
}
