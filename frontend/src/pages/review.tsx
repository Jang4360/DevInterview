import React, { useState } from "react";
import Input from "@/components/common/Input";
import Modal from "@/components/common/Modal";
import { FiTrash2 } from "react-icons/fi";
import Link from "next/link";

const dummyData = [
  { id: 1, question: "ERD 검토", lastReview: "2025/03/21", reviewCount: 1 },
  { id: 2, question: "트랜잭션", lastReview: "2025/03/21", reviewCount: 1 },
  { id: 3, question: "동시성", lastReview: "2025/03/21", reviewCount: 1 },
];

export default function ReviewPage() {
  const [searchTerm, setSearchTerm] = useState("");
  const [data, setData] = useState(dummyData);
  const [modalOpen, setModalOpen] = useState(false);
  const [selectedId, setSelectedId] = useState<number | null>(null);

  const filteredData = data.filter((item) =>
    item.question.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const openModal = (id: number) => {
    setSelectedId(id);
    setModalOpen(true);
  };

  const confirmDelete = () => {
    setData(data.filter((item) => item.id !== selectedId));
    setModalOpen(false);
    setSelectedId(null);
  };

  return (
    <div className="p-6">
      {/* 검색창 */}
      <div className="mb-6">
        <Input
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          placeholder="키워드로 검색 하세요"
          className="w-full bg-[#303030]"
        />
      </div>

      {/* QnA 리스트 */}
      <div className="bg-[#303030] rounded-lg p-4">
        <table className="w-full text-left text-white">
          <thead>
            <tr className="border-b border-gray-600">
              <th className="p-2">면접 질문</th>
              <th className="p-2">마지막 리뷰</th>
              <th className="p-0">리뷰 횟수</th>
              <th className="p-2"></th>
            </tr>
          </thead>
          <tbody>
            {filteredData.map((item) => (
              <tr key={item.id} className="border-b border-gray-700">
                <td className="p-2">
                  <Link href={`/review/${item.id}`}>
                    <p className="text-white hover:underline">
                      {item.question}
                    </p>
                  </Link>
                </td>
                <td className="p-2">{item.lastReview}</td>
                <td className="p-3">{item.reviewCount}</td>
                <td className="p-4">
                  <button onClick={() => openModal(item.id)}>
                    <FiTrash2
                      size={16}
                      className="text-white hover:text-red-500"
                    />
                  </button>
                </td>
              </tr>
            ))}
            {filteredData.length === 0 && (
              <tr>
                <td colSpan={4} className="p-4 text-center text-gray-400">
                  검색 결과가 없습니다.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>

      {/* 삭제 확인 모달 */}
      <Modal
        isOpen={modalOpen}
        onConfirm={confirmDelete}
        onCancel={() => setModalOpen(false)}
        message="삭제하시겠습니까?"
      />
    </div>
  );
}
